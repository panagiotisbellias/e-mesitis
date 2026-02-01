# 🧩 Contributing Guidelines (Internal)

Welcome to **E Mesitis**!  
These guidelines help internal contributors collaborate consistently and effectively across the project.

---

## 🧱 1. Development Workflow

1. **Create a feature branch**
   ```bash
   git checkout -b feature/<short-description>
   ```
   Prefixes:
   - `feature/` – new features  
   - `fix/` – bug fixes  
   - `refactor/` – structural improvements  
   - `docs/` – documentation changes  
   - `test/` – unit/integration tests

2. **Write meaningful, atomic commits**
   - Present tense, imperative (e.g., “Add validation for tenant email”).  
   - Small, self‑contained commits.  
   - Reference issues when applicable.

3. **Open a Merge Request (MR)**
   - Target `development` unless instructed otherwise.  
   - Provide a clear description and testing notes.  
   - Request review from the relevant maintainer.

---

## 🧪 2. Code Quality Standards

- Follow the existing layered architecture.  
- Add unit and integration tests.  
- Avoid code duplication; prefer service abstraction.  
- Ensure no Sonar warnings remain.

---

## 🧰 3. Code Review

Reviewers validate:
- Readability and maintainability  
- Proper error handling and logging  
- Adherence to project conventions  
- Test coverage and correctness

Merging requires at least **one approval**.

---

## 📘 4. Documentation

Update documentation when relevant:
- `README.md` for setup or environment changes  
- Inline comments for complex logic

---

## 🧭 5. Commit Convention

Follow **Conventional Commits**:

```
<type>(<scope>): <description>
```

Examples:
- `feat(auth): implement token refresh`
- `fix(ui): resolve overlapping labels in tenant card`
- `refactor(api): simplify rental service`
- `docs(guides): update deployment instructions`

---

## 🧩 6. Communication

Use:
- **GitHub issues** for tracking features or bugs  
- English for commit messages and comments

---

_Last updated: November 2025_
