# 🔒 Security Policy

## 1. Overview

The **E Mesitis** project is committed to maintaining a secure development and usage environment.
This document defines security expectations, reporting procedures, and secret-handling rules.

---

## 2. Supported Versions

Security updates apply only to active branches:

| Branch                | Status          |
|-----------------------|-----------------|
| main (production)     | ✅ Supported     |
| development (staging) | ✅ Supported     |
| older releases        | ❌ Not supported |

---

## 3. Reporting a Vulnerability

If you identify a potential security issue, **do not create a public issue**.

Instead, report it privately to:

📧 **[belliaspan@gmail.com](mailto:belliaspan@gmail.com)**

Please include when possible:

* Clear description of the vulnerability
* Steps to reproduce
* Potential impact or affected modules
* Relevant logs or screenshots (if safe to share)

We aim to acknowledge reports within **2 business days** and provide mitigation within **10 business days**.

---

## 4. Credential & Secret Handling

* **No secrets are stored in the repository**
* Credentials must never be hardcoded or committed
* Secrets must be injected via:

  * GitHub Actions Secrets
  * Environment variables
* If a secret is exposed:

  1. Revoke it immediately
  2. Rotate the credential
  3. Remove it from the repository
  4. Clean repository history if required

---

## 5. Dependency & Supply Chain Security

* Dependencies should be kept up to date
* Automated dependency scanning is enabled via CI
* Avoid introducing unmaintained or unverified libraries

---

## 6. Secure Coding Practices

Contributors are expected to:

* Validate all external input
* Apply least-privilege principles
* Avoid hardcoded configuration values
* Prevent sensitive data from appearing in logs

---

## 7. CI/CD & Deployment Security

* All credentials must be stored in CI secret managers
* Secrets must never be echoed in pipeline logs
* Rotate credentials periodically
* Review workflows for accidental data exposure

---

## 8. Repository History & Incident Response

If secrets are accidentally committed:

1. Remove them from the working tree
2. Clean the repository history (e.g. `git filter-repo`)
3. Rotate affected credentials immediately
4. Inform contributors to re-clone the repository

---

## 9. Updates

This document evolves as security practices mature. Contributors are expected to stay informed of updates.

---

*Last updated: February 2026*
