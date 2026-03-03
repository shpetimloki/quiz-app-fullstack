"use client";
import { useEffect, useState, use, useCallback } from "react";
import Link from "next/link";

interface Question {
  id: number;
  text: string;
  options: string[];
}

export default function QuizPage({ params }: { params: Promise<{ id: string }> }) {
  const resolvedParams = use(params);
  const id = resolvedParams.id;

  const [question, setQuestion] = useState<Question | null>(null);
  const [loading, setLoading] = useState(true);
  const [feedback, setFeedback] = useState<{ isCorrect: boolean; selectedIndex: number } | null>(null);
  const [finished, setFinished] = useState(false);

  const loadNextQuestion = useCallback(async (isInitial = false) => {
    if (!isInitial) setLoading(true);
    
    try {
      const res = await fetch(`http://localhost:8080/api/categories/${id}/next-question`);
      const data = await res.json();
      
      if (data.message) {
        setFinished(true);
      } else {
        setQuestion(data);
        setFeedback(null); // Reset Feedback für die neue Frage
      }
    } catch (err) {
      console.error("Fehler beim Laden:", err);
    } finally {
      setLoading(false);
    }
  }, [id]);

  useEffect(() => {
    loadNextQuestion(true);
  }, [loadNextQuestion]);

  const handleAnswer = async (index: number) => {
    if (feedback || !question) return;

    const res = await fetch(`http://localhost:8080/api/questions/${question.id}/answer`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ answerIndex: index }),
    });
    const result = await res.json();

    setFeedback({ isCorrect: result.correct, selectedIndex: index });

    setTimeout(() => {
      loadNextQuestion();
    }, 1500);
  };

  if (loading && !question) {
    return <div className="min-h-screen bg-black text-white flex items-center justify-center text-xl">Lade Frage...</div>;
  }

  if (finished) {
    return (
      <main className="min-h-screen bg-black text-white flex flex-col items-center justify-center p-10 text-center">
        <h2 className="text-5xl font-black mb-6">GESCHAFFT! 🏆</h2>
        <p className="text-zinc-400 text-xl mb-10">Alle Fragen dieser Kategorie wurden gelöst.</p>
        <Link href="/" className="px-10 py-4 bg-blue-600 rounded-2xl font-bold hover:bg-blue-500 transition-all transform hover:scale-105 shadow-2xl">
          ZUR ÜBERSICHT
        </Link>
      </main>
    );
  }

  return (
    <main className="min-h-screen bg-black text-white p-10 flex flex-col items-center">
      <div className="max-w-2xl w-full">
        <Link href="/" className="text-zinc-500 hover:text-white mb-10 inline-block transition-colors">← ABBRECHEN</Link>
        
        <h2 className="text-4xl font-black mb-10 leading-tight">
          {question?.text}
        </h2>
        
        <div className="grid gap-4">
          {question?.options.map((option, index) => {
            let buttonStyle = "bg-zinc-900 border-zinc-800 hover:border-zinc-500 text-white";
            
            if (feedback && feedback.selectedIndex === index) {
              buttonStyle = feedback.isCorrect 
                ? "bg-green-600 border-green-400 text-white" 
                : "bg-red-600 border-red-400 text-white";
            } else if (feedback) {
              buttonStyle = "bg-zinc-900 border-zinc-800 opacity-40 text-zinc-500";
            }

            return (
              <button 
                key={index}
                onClick={() => handleAnswer(index)}
                disabled={!!feedback}
                className={`p-5 border rounded-2xl text-left transition-all duration-300 text-lg font-medium shadow-lg ${buttonStyle}`}
              >
                {option}
              </button>
            );
          })}
        </div>

        {feedback && (
          <p className={`mt-10 text-center font-bold text-2xl animate-pulse ${feedback.isCorrect ? "text-green-400" : "text-red-500"}`}>
            {feedback.isCorrect ? "RICHTIG! 🎉" : "FALSCH! ❌"}
          </p>
        )}
      </div>
    </main>
  );
}