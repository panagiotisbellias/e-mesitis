# e-mesitis

![CI](https://github.com/panagiotisbellias/e-mesitis/actions/workflows/ant-build.yml/badge.svg)

**Version:** V2.0.0  

Highlights:
- Core CLI use cases (UC1–UC7) implemented
- In-memory data only (persistence and stabilization planned for future releases)
- Initial project documentation: README.md, CHANGELOG.md, ADR log, ROADMAP.md
- License and NOTICE files included for open-source compliance
- Basic build/test workflow using Ant
- Semantic versioning enabled

Notes for Contributors:
- [Unreleased] section in CHANGELOG.md should be used for ongoing changes
- ADRs log documents key architectural decisions
- Please follow coding and testing conventions when submitting PRs

---

## 🧩 Overview

This is the initial structure for the **e-mesitis** desktop Java application.  
Currently, the application contains only the entry point class `e.mesitis.EMesitis` with no logic implemented.

The goal of this version is to validate the build and run pipeline using **Apache Ant**.

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
dist/e-mesitis_V2.0.0.jar
```

---

## ▶️ Run (for verification)

Even though the app does nothing yet, you can run it to confirm the setup works:

```bash
java -cp dist/e-mesitis_V2.0.0.jar e.mesitis.EMesitis
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

The project uses **GitHub Actions** to verify the build on every push and pull request.

Current CI pipeline:
- Runs on Ubuntu
- Sets up JDK 17
- Executes `ant clean jar`

This ensures the project always builds successfully using Apache Ant.

(ADR0002 – CI setup with GitHub Actions)

---

## 🧭 Next Steps

- Add data persistence (save/load from file)
- Introduce Swing or JavaFX interface
- Add validation and exception handling
- Configure unit tests and continuous integration
- UC8 - Generate Rental Report

---

© 2026 Panagiotis Bellias
