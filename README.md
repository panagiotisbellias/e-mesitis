# e-mesitis

**Version:** V0.0  
**Status:** Initial setup — basic Ant build verified, no implemented logic yet.

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
dist/e-mesitis_V0.0.jar
```

---

## ▶️ Run (for verification)

Even though the app does nothing yet, you can run it to confirm the setup works:

```bash
java -cp dist/e-mesitis_V0.0.jar e.mesitis.EMesitis
```

Expected result:
```
(no output — empty main method)
```

---

## 🧹 Clean

To remove build outputs:

```bash
ant clean
```

---

## 🧭 Next Steps

- Implement initial application logic in `src/e/mesitis/EMesitis.java`
- Add UI or CLI entry points
- Configure tests and CI/CD with Ant targets

---

© 2025 Panagiotis Bellias
