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
INFO: === Register a New Residence ===
INFO: Enter type (1 = Apartment, 2 = Detached House): 1
INFO: Municipality: Athens
INFO: Area (m²): 85
INFO: Construction Year: 2010
INFO: Bedrooms: 2
INFO: Bathrooms: 1
INFO: Rental Price (€): 850
INFO: Floor: 3rd
INFO: Has parking spot (true/false): true
INFO: Has warehouse (true/false): false
```

Expected log output:
```
INFO: === Register a New Residence ===
INFO: Residence successfully registered: Apartment in Athens (85.0 m², 2 beds, 1 baths, floor: 3rd, parking: true, warehouse: false)
INFO: Residence registration completed successfully.
```

---

## 🧹 Clean

To remove build outputs:

```bash
ant clean
```

---

## 🧭 Next Steps

- UC2 – List All Residences
- Add data persistence (save/load from file)
- Introduce Swing or JavaFX interface
- Add validation and exception handling
- Configure unit tests and continuous integration

---

© 2025 Panagiotis Bellias
