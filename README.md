# Amazon_405 - Next Level (Patched)

## Run with Docker Compose (recommended)
1. Build & run:
```
docker-compose up --build
```
2. App will be available at `http://localhost:8080`

## Env vars
- DB_URL, DB_USER, DB_PASSWORD can be set in docker-compose or environment.

## Notes
- Passwords are stored hashed (BCrypt).
- Spring Security is enabled. Default login page at `/login`.
- Admin dashboard at `/admin/dashboard` (requires ADMIN role).
