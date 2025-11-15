# 🔒 Security Policy

## 1. Overview
The **E Mesitis** project is committed to ensuring a secure environment for all contributors and users.  
This document defines the initial security considerations and reporting procedures for internal use.

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

📧 **belliaspan@gmail.com**

Include when possible:
- Clear description of the vulnerability  
- Steps to reproduce  
- Potential impact or affected modules  
- Relevant logs or screenshots (if safe to share)

We aim to acknowledge reports within **2 business days** and provide mitigation within **10 business days**.

---

## 4. Security Best Practices for Contributors

### Avoid exposing sensitive data
- Never commit `.env` files, keys, tokens, or credentials  
- Do not log sensitive personal or system data  
- Use secure storage mechanisms for secrets (Vault, CI/CD variables)

### Maintain dependency security
- Run dependency checks regularly:  
  - `mvn dependency-check`  
- Avoid introducing unverified or unmaintained libraries

### Follow secure coding practices
- Validate all input data  
- Apply least-privilege principles for permissions  
- Avoid hardcoding configuration values

---

## 5. Handling Sensitive Data
- Mask sensitive information in logs  
- Use encryption for data at rest and in transit  
- Do not expose internal URLs, system paths, or infrastructure details in public artifacts  
- Follow GDPR and internal data privacy policies when handling personal data  

---

## 6. CI/CD & Deployment Security
- Store all credentials in the CI secret manager  
- Rotate keys periodically and revoke unused credentials  
- Ensure pipelines do not echo sensitive variables  
- Review deployment configurations for accidental exposures  

---

## 7. History Cleanup & Secret Removal
If secrets were accidentally committed:
1. Remove them from the working tree  
2. Clean the repository history (`git filter-repo`)  
3. Rotate affected credentials immediately  
4. Inform the team to re-clone the repository  

(See internal *Appendix B – Secret Handling & Rotation* for full procedure.)

---

## 8. Updates
This document will evolve as additional security procedures are formalized.  
Contributors are expected to read the updated version when notified.

---

_Last updated: November 2025_
