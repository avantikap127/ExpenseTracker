# ExpenseTracker (Java)

This small Java project is set up without Maven/Gradle. It relies on the `lib/gson-2.10.jar` JAR for JSON handling and can be built with a JDK.

Goal: target Java 21 (latest LTS).

Prerequisites
- Install JDK 21 (Eclipse Adoptium Temurin or similar).
- Set `JAVA_HOME` to the JDK installation directory or ensure `javac`/`java` are on your PATH.

Quick steps (PowerShell):

1) Build

```powershell
.\build.ps1
```

2) Run

```powershell
.\run.ps1
```

Notes
- The build script compiles all `.java` files with `--release 21` to ensure compatibility with Java 21.
- If you prefer to use a build tool (Maven/Gradle), I can add `pom.xml` or `build.gradle` and migrate the project.

If you want, I can also attempt to install JDK 21 automatically (requires permission) or add a small `javac` wrapper to pick a local installed JDK. Let me know which you'd prefer.