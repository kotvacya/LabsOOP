/** @type {import('next').NextConfig} */
const nextConfig = {
    async rewrites() {
        return [
            {
                source: "/api/:path*",
                destination: "http://backend:8080/:path*",
            },
        ];
    },
    skipTrailingSlashRedirect: true,
    output: "standalone",
};

export default nextConfig;
