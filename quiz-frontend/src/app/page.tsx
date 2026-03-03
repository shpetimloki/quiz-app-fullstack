"use client";
import { useEffect, useState } from "react";
import Link from "next/link";

interface Category {
  id: number;
  name: string;
  correctCount: number;
  totalCount: number;
  percent: number;
}

export default function Home() {
  const [categories, setCategories] = useState<Category[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetch("http://localhost:8080/api/categories")
      .then((res) => res.json())
      .then((data) => {
        setCategories(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Backend-Fehler:", err);
        setLoading(false);
      });
  }, []);

  return (
    <main className="flex min-h-screen flex-col items-center justify-center p-24 bg-black text-white">
      <h1 className="text-6xl font-black mb-12 tracking-tighter italic">
        QUIZ<span className="text-blue-500">APP</span>
      </h1>

      <div className="w-full max-w-md space-y-6">
        {loading ? (
          <div className="text-center animate-pulse text-gray-500">Lade Kategorien...</div>
        ) : categories.length > 0 ? (
          categories.map((cat) => (
            <Link href={`/quiz/${cat.id}`} key={cat.id} className="block group">
              <div className="w-full p-6 bg-zinc-900 border border-zinc-800 rounded-2xl group-hover:border-blue-500 transition-all shadow-xl">
                <div className="flex justify-between items-center mb-4">
                  <span className="text-xl font-bold uppercase tracking-wide">{cat.name}</span>
                  <span className="text-sm font-mono text-zinc-500">
                    {cat.correctCount} / {cat.totalCount}
                  </span>
                </div>
                
                {/* Fortschrittsbalken */}
                <div className="w-full bg-zinc-800 h-2 rounded-full overflow-hidden">
                  <div 
                    className="bg-blue-500 h-full transition-all duration-700 ease-out" 
                    style={{ width: `${cat.percent}%` }}
                  ></div>
                </div>
              </div>
            </Link>
          ))
        ) : (
          <div className="text-red-400 text-center">Keine Daten gefunden.</div>
        )}
      </div>
    </main>
  );
}