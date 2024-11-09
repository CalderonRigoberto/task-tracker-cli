# Task Tracker CLI

A command-line task tracking application built as part of my Java learning journey following the [roadmap.sh]([https://roadmap.sh](https://roadmap.sh/projects/task-tracker)) path. This project demonstrates practical implementation of modern Java features while helping others learn through real-world examples.

## About This Project

This Task Tracker is a practical exercise in building command-line applications using Java, focusing on modern language features and clean code practices. It's part of a series of projects aimed at improving Java skills through hands-on experience.

## Key Technologies & Concepts Used

- **Modern Java Features**
    - Records for immutable data structures
    - Stream API for data processing
    - Local variable type inference (var)
    - Pattern Matching (Java 17+)

- **Libraries**
    - Jackson for JSON file handling
        - JSON reading/writing
        - Object mapping
        - Data persistence

- **Project Purpose**
    - Practice modern Java features
    - Learn file handling and data persistence
    - Implement CLI interfaces
    - Apply clean code principles

## Learning Outcomes

Through this project, I practiced:
- Building interactive CLI applications
- Working with file systems using Java
- Data persistence using JSON
- Modern Java features in a real-world context
- Command pattern implementation

## Installation

Detailed instructions for installing and running the Task Tracker CLI are available in the Getting Started section below.

## Getting Started

1. Ensure you have Java 17+ installed
2. Clone the repository
3. Build the project using Maven
4. Add to your system's PATH for easy access

Example:
````shell
# 1. Create bin directory if it doesn't exist
mkdir -p ~/bin

# 2. Create the executable script
nano ~/bin/task-tracker

# In the script, add:
#!/bin/bash
# If your project is on Linux:
java -jar /path/to/your/project/target/task-tracker-cli-1.0-SNAPSHOT.jar "$@"

# In my case, since the project is on Windows but I'm using it through WSL:
java -jar /mnt/c/Users/example/Desktop/task-tracker-cli/target/task-tracker-cli-1.0-SNAPSHOT.jar "$@"
````
Important Note:

The /mnt/c/... path is specific for accessing Windows files from WSL
If your project is directly on Linux, use the standard Linux path like /home/user/projects/... or ~/projects/...
Make sure the path points to the JAR file generated in your project's target directory

````shell
# 3. Make the script executable
chmod +x ~/bin/task-tracker

# 4. Add bin to PATH in .zshrc
nano ~/.zshrc
export PATH="$HOME/bin:$PATH"

# 5. Reload the configuration
source ~/.zshrc
This method allows you to have your project on Windows, compile it there, but execute it conveniently from WSL as a regular command.
````

## Community and Learning

This project is part of my learning journey, and I encourage others to:
- Fork the repository and experiment with the code
- Suggest improvements and new features
- Share their own learning experiences
- Use it as a reference for their Java learning journey

## Why This Project?

- Practical application of Java concepts
- Real-world use case implementation
- Part of structured learning through roadmap.sh
- Opportunity to share knowledge with the community

## Contributing

Feel free to contribute to this project! Whether you're:
- Learning Java yourself
- Have suggestions for improvements
- Want to add new features
- Found bugs to fix

Your contributions are welcome!

## About the Author

Hi! I'm Rigoberto Calderón, a software developer passionate about learning and sharing knowledge with the community.

- 🌐 Portfolio: [calderonrigoberto.github.io](https://calderonrigoberto.github.io/)
- 💻 GitHub: [@CalderonRigoberto](https://github.com/CalderonRigoberto)
- 📚 Currently following the Java path on roadmap.sh to enhance my skills
- 🤝 Open to collaborations and learning opportunities

Check out my other projects and learning journey on my GitHub profile!

## License

This project is open source and available under the MIT License.

---

Built with ❤️ as part of my Java learning journey.
