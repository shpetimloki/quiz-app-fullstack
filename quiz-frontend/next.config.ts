import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  experimental: {
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-expect-error
    reactCompiler: true,
  },
};

export default nextConfig;