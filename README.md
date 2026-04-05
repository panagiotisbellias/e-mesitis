# e-mesitis

![CI](https://github.com/panagiotisbellias/e-mesitis/actions/workflows/ant-build.yml/badge.svg)

**Version:** V2.0.1

Notes for Contributors:
- section in CHANGELOG.md should be used for ongoing changes
- ADRs log documents key architectural decisions
- Please follow coding and testing conventions when submitting PRs

---

## 🧩 Overview

This is the initial structure for the **e-mesitis** desktop Java application.  
This project implements a desktop-based real estate management system with:

- Residence management (apartments, detached houses)
- Search and filtering
- Average rent calculations
- DAO layer with H2 database support
- Unit and integration testing

---

## 🤝 Contributing

We welcome contributions!

- Read: `CONTRIBUTING.md`
- Pick an issue labeled `good first issue`
- Submit a PR to `development`

---

## 🚀 Release Process

Releases are automated via GitHub Actions:

- Tag format: `vX.Y.Z`
- Example:
  ```bash
  git tag v2.0.1
  git push origin v2.0.1
  ```

This will:

- Build the project
- Run tests
- Publish a GitHub Release with artifacts

---

📊 Code Quality

The project includes:

- Checkstyle
- PMD
- SpotBugs
- CodeQL
- Qodana (JetBrains)

---

🧪 Testing

Run all tests:
```bash
ant clean test
```

Run with coverage:
```bash
ant coverage
```

---

## ⚙️ Requirements

- **Java JDK 15+**
- **Apache Ant**

Check setup:
```bash
java -version
ant -version
```

---

## 🏗️ Build

To compile and package the project into a JAR:

```bash
ant jar
```

Output:
```
dist/e-mesitis_V2.0.1.jar
```

---

## ▶️ Run (for verification)

Even though the app does nothing yet, you can run it to confirm the setup works:

```bash
java -cp dist/e-mesitis_V2.0.1.jar e.mesitis.EMesitis
```

Expected result:
```
e.mesitis.EMesitis main
INFO: === e-Mesitis ===
1. Register a new residence
2. View available residences
3. Search residence by criteria
4. Update residence information
5. Delete residence
6. Calculate average rent per municipality
7. View residence details
0. Exit
Choose option:
```

---

## 🧹 Clean

To remove build outputs:

```bash
ant clean
```

---

## 🤖 Continuous Integration

The project uses **GitHub Actions** for:

- Build & test validation
- Static code analysis (CodeQL, SpotBugs, PMD)
- Automated releases on version tags

Current CI pipeline:
- Runs on Ubuntu
- Sets up JDK 17
- Executes `ant clean jar`

This ensures the project always builds successfully using Apache Ant.

Workflows:
- `ant-build.yml` – build & test
- `codeql.yml` – security analysis
- `release.yml` – automated releases

(ADR0002 – CI setup with GitHub Actions)

---

## 📚 Documentation

Project documentation is organized as follows:

- `README.md` – Project overview and setup
- `CONTRIBUTING.md` – Contribution guidelines
- `CHANGELOG.md` – Version history
- `ROADMAP.md` – Planned features and direction
- `docs/adr/` – Architecture Decision Records (ADRs)

---

## 🧭 Architecture Decisions

Key design decisions are documented as ADRs.

Example topics:
- CI/CD setup
- Testing strategy
- Build tooling (Ant)

---

## 🧭 Next Steps

- Add data persistence (save/load from file)
- Introduce Swing or JavaFX interface
- Add validation and exception handling
- Configure unit tests and continuous integration
- UC8 - Generate Rental Report

---

© 2026 Panagiotis Bellias
