
# GeRuMap

**GeRuMap** is a desktop application developed as a part of the "Software Design" course by Nikola Ivancev and Luka Vukadinovic. The primary goal of this project is to implement the Model-View-Controller (MVC) architecture while demonstrating the use of various design patterns to create a flexible and maintainable software system.

## Project Overview

GeRuMap is designed to help users create Mind Maps—a structured way of organizing ideas and information visually. This application allows users to create, edit, and manage Mind Maps, which can be grouped into projects. The Mind Maps can be saved and later imported, ensuring that work can be continued seamlessly.

### Key Features:
- **Create and Manage Projects**: Organize multiple Mind Maps into projects.
- **Customizable Mind Maps**: Users can create their own templates or use existing patterns, with the ability to customize topics, associations, colors, and text.
- **Import and Export**: Save your Mind Maps as images or export them for future editing.
- **Design Patterns Implemented**:
  - **Singleton**: Ensures a single instance of a class.
  - **Observer**: Establishes a subscription mechanism to notify changes.
  - **State Pattern**: Manages states and transitions in the application.
  - **Command Pattern**: Encapsulates requests as objects, allowing for flexible command execution.
  - **Composite Pattern**: Structures objects into tree-like hierarchies to represent part-whole hierarchies.
  - **Factory Method**: Defines an interface for creating objects but allows subclasses to alter the type of objects that will be created.

## Contributors

- **Nikola Ivancev** (5321 RN)
- **Luka Vukadinovic** (2921 RN)


### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/nivancev-raf/GeRuMap.git
   ```
2. Open the project in your favorite IDE (e.g., IntelliJ IDEA, VS Code).
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the application:
   - Navigate to `src/main/java`
   - Execute the `AppCore.java` file.

### Usage

- **Create a Project**: Start by creating a new project, where all your Mind Maps will be stored.
- **Create a Mind Map**: Choose a predefined pattern or start from scratch to create your Mind Map. Customize topics and associations to structure your information logically.
- **Edit Mind Maps**: Modify your maps by adding or removing topics, changing colors, or editing text. Reorganize the map by moving elements and centering around a main topic.
- **Export Maps**: Save your work as an image or export it for later use. You can also import previously saved maps to continue working on them.

## Notion Links

- **Project Specification**: [GeRuMap Specification](https://evergreen-tablecloth-b36.notion.site/GeRuMap-Specifikacija-07b4e67b71de436584df910bd271f7df)
- **Class Diagram**: [Class Diagram](https://www.notion.so/Klasni-dijagram-34ae2aefe312428bb828d3fdf2a387ac)
- **DIN**: [DIN](https://www.notion.so/DIN-990484e18cc54abb8aaf019bbdc8801f)

## Contact
For any questions or feedback, please reach out to nivancev02@gmail.com.
