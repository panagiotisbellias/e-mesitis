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
   - Target `development` or the related `release/x` unless instructed otherwise.  
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

## 🔀 7. Merge Strategy

The project uses **Squash and Merge** when integrating changes into protected branches.

Why squash merge:

* Keeps commit history clean and readable
* One logical change results in one commit on the target branch
* Simplifies reverts if needed

Merge requirements:

* At least **one approval** from a reviewer
* All required CI checks must pass
* Branch must be up to date with the target branch

---

## 🔒 8. Branch Protection Rules

For protected branches (e.g. `main`, `development`, `release/v2.0.0`), the following rules are recommended:

* Require pull request reviews before merging
* Require status checks to pass before merging
* Require branches to be up to date before merging
* Disallow direct pushes to protected branches

Optional but recommended:

* Dismiss stale approvals when new commits are pushed

---

## 📋 9. Pull Request Template

All contributors should follow the Pull Request template located at:

```
.github/pull_request_template.md
```

The template helps reviewers by ensuring:

* Clear summary of changes
* Proper issue linkage
* Documented testing steps
* Basic quality checks

---

## 🧪 10. Continuous Integration

All pull requests are automatically validated by CI workflows (build, tests, security checks).

A pull request **cannot be merged** unless:

* CI pipelines pass successfully
* No blocking issues are reported by automated checks

---

*Last updated: January 2026*
