# Contributing to HySuite

First off, thank you for considering contributing to HySuite! It's people like you that make HySuite such a great tool for the Hytale community.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How Can I Contribute?](#how-can-i-contribute)
- [Development Setup](#development-setup)
- [Pull Request Process](#pull-request-process)
- [Style Guidelines](#style-guidelines)
- [Community](#community)

---

## 📜 Code of Conduct

This project and everyone participating in it is governed by our commitment to:

- **Be respectful** - Treat everyone with respect and kindness
- **Be welcoming** - Welcome newcomers and help them get started
- **Be collaborative** - Work together and share knowledge
- **Be professional** - Keep discussions constructive and on-topic

By participating, you are expected to uphold these values. Unacceptable behavior should be reported to the project maintainers.

---

## 🤝 How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check existing issues to avoid duplicates. When creating a bug report, include:

**Required Information:**
- **Clear title** - Describe the issue concisely
- **Steps to reproduce** - Detailed steps to recreate the bug
- **Expected behavior** - What should happen
- **Actual behavior** - What actually happens
- **Environment** - Hytale version, Java version, OS
- **Logs** - Relevant error messages or stack traces
- **Screenshots** - If applicable

**Example:**
**Title:** Home GUI doesn't open when clicking /homes

**Steps to Reproduce:**
1. Join server with HySuite 1.0.0
2. Type `/homes` command
3. Press enter

**Expected:** Home GUI should open
**Actual:** Nothing happens, no error message

**Environment:**
- HySuite: 1.0.0
- Hytale: Latest
- Java: 17
- OS: Windows 11

**Logs:**
[Paste relevant logs here]
```

### Suggesting Features

We love feature suggestions! Before submitting:

1. **Check the roadmap** - Your idea might already be planned
2. **Search existing issues** - Someone may have suggested it already
3. **Be detailed** - Explain the use case and benefits

**Template:**
```markdown
**Feature:** [Brief description]

**Problem it solves:**
[Describe the problem or limitation]

**Proposed solution:**
[Explain your idea in detail]

**Alternatives considered:**
[Other approaches you thought about]

**Additional context:**
[Screenshots, mockups, examples]
```

### Contributing Code

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Make your changes**
4. **Test thoroughly**
5. **Commit with clear messages**
6. **Push to your fork**
7. **Open a Pull Request**

### Contributing Documentation

Documentation improvements are always welcome! This includes:

- Fixing typos or clarifying instructions
- Adding examples or use cases
- Translating to other languages
- Improving API documentation
- Creating tutorials or guides

### Helping Others

You can contribute by:

- Answering questions on Discord
- Helping troubleshoot issues
- Creating tutorials or videos
- Sharing your server setup
- Promoting the plugin

---

## 🛠️ Development Setup

### Prerequisites

- **Java 17 or higher**
- **Maven 3.6+**
- **Git**
- **Hytale Server** (for testing)
- **IDE** - IntelliJ IDEA recommended
- **vscode** - Also recommended

### Initial Setup

```bash
# 1. Fork and clone the repository
git clone https://github.com/YOUR-USERNAME/HySuite.git
cd HySuite

# 2. Set HYTALE_HOME environment variable
# Windows:
set HYTALE_HOME=C:\Users\[username]\AppData\Roaming\Hytale\install\release\package\game\latest

# Linux/Mac:
export HYTALE_HOME=/path/to/hytale

# 3. Build the project
mvn clean package

# 4. JAR will be in target/hysuite-*.jar
```

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=HomeManagerTest

# Run with coverage
mvn test jacoco:report
```

### Development Workflow

1. **Create a branch** for your feature/fix
2. **Make changes** in small, logical commits
3. **Test locally** on a Hytale server
4. **Update documentation** if needed
5. **Run tests** to ensure nothing broke
6. **Push changes** and create PR

### Building

# Clean build
mvn clean package

# Skip tests (faster, for local testing only)
mvn clean package -DskipTests

# Install to local Maven repository
mvn clean install

### Testing on Server

# Copy JAR to server plugins folder
copy target\hysuite-1.0.0.jar %HYTALE_HOME%\Server\plugins\

# Start server and test your changes
# Check logs: %HYTALE_HOME%\Server\logs\latest.log

---

## 📝 Pull Request Process

### Before Submitting

- [ ] Code follows project style guidelines
- [ ] All tests pass
- [ ] New features have tests
- [ ] Documentation is updated
- [ ] Commits are clear and descriptive
- [ ] Branch is up-to-date with main

### PR Title Format

Use descriptive titles with type prefix:

```
feat: Add home sharing system
fix: Resolve GUI crash when opening warps
docs: Update installation guide
refactor: Improve TPA manager performance
test: Add tests for home manager
```

### PR Description Template

```markdown
## Description
[Clear description of what this PR does]

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Related Issues
Fixes #[issue number]

## Testing
[Describe how you tested this]

## Screenshots
[If applicable]

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] No new warnings
- [ ] Tests added/updated
- [ ] All tests pass
```

### Review Process

1. **Automated checks** - CI must pass
2. **Code review** - Maintainer will review
3. **Changes requested** - Address feedback
4. **Approval** - Once approved, PR will be merged
5. **Merge** - Maintainer will merge and close

### After Merging

- Your contribution will be in the next release
- You'll be added to contributors list
- Thank you! 🎉

---

## 🎨 Style Guidelines

### Java Code Style

**General Principles:**
- Follow Java naming conventions
- Keep methods focused and small
- Use meaningful variable names
- Comment complex logic
- Avoid deep nesting

**Formatting:**
```java
// Class names: PascalCase
public class HomeManager {
    
    // Constants: UPPER_SNAKE_CASE
    private static final int MAX_HOMES = 10;
    
    // Variables: camelCase
    private final Map<UUID, List<Home>> playerHomes;
    
    // Methods: camelCase
    public void setHome(UUID playerId, String homeName, Location location) {
        // Method implementation
    }
}
```

**Code Organization:**
```java
public class ExampleClass {
    // 1. Static constants
    private static final String PREFIX = "[HySuite]";
    
    // 2. Instance variables
    private final DataManager dataManager;
    private Map<UUID, PlayerData> playerData;
    
    // 3. Constructor
    public ExampleClass(DataManager dataManager) {
        this.dataManager = dataManager;
        this.playerData = new HashMap<>();
    }
    
    // 4. Public methods
    public void publicMethod() {
        // Implementation
    }
    
    // 5. Private methods
    private void privateHelper() {
        // Implementation
    }
}
```

**Comments:**
```java
/**
 * Manages player homes including setting, deleting, and teleporting.
 * 
 * @author Buddytohelpu
 * @version 1.0.0
 */
public class HomeManager {
    
    /**
     * Sets a home for a player at their current location.
     *
     * @param playerId The UUID of the player
     * @param homeName The name of the home
     * @param location The location to save
     * @return true if home was set successfully
     */
    public boolean setHome(UUID playerId, String homeName, Location location) {
        // Complex logic should have inline comments
        if (!canSetHome(playerId)) {
            return false; // Player has reached home limit
        }
        
        // Continue implementation...
        return true;
    }
}
```

### UI File Style

**Naming:**
- Always prefix with `HySuite_`
- Use PascalCase: `HySuite_HomeList.ui`
- Be descriptive: `HySuite_PlayerAssignEntry.ui`

**Structure:**
```xml
<!-- Keep UI files clean and organized -->
<ui>
    <!-- Group related elements -->
    <panel id="main">
        <!-- Clear, descriptive IDs -->
        <button id="teleportButton">Teleport</button>
        <text id="homeNameLabel">Home Name</text>
    </panel>
</ui>
```

### JSON Configuration Style

**Formatting:**
```json
{
  "setting": "value",
  "nested": {
    "property": true
  },
  "array": [
    "item1",
    "item2"
  ]
}
```

**Best Practices:**
- Use 2-space indentation
- Keep consistent formatting
- Add comments in documentation, not JSON
- Validate JSON before committing

### Commit Message Style

**Format:**
```
type(scope): Short description

Longer explanation if needed.

Fixes #issue-number
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation
- `style`: Formatting
- `refactor`: Code restructuring
- `test`: Adding tests
- `chore`: Maintenance

**Examples:**
```
feat(homes): Add home sharing system

Implements ability to share homes with other players.
Includes new commands and GUI updates.

Fixes #123

---

fix(gui): Resolve crash when opening warp list

The warp list GUI was crashing when no warps existed.
Added null check and empty state message.

Fixes #456

---

docs(readme): Update installation instructions

Clarified the HYTALE_HOME environment variable setup
for Windows users.
```

---

## 🌐 Community

### Discord Server

Join our Discord for:
- Real-time discussions
- Getting help with contributions
- Sharing ideas
- Testing new features

**Link:** [discord.gg/vPHmVASQAN]

### GitHub Discussions

Use GitHub Discussions for:
- Feature proposals
- General questions
- Showcasing your server
- Plugin ideas

### Social Media

Follow us for updates :
- **Github:** Release Notes
- **Discord:** Link Above

---

## 🏆 Recognition

Contributors are recognized in multiple ways:

- **GitHub Contributors** - Automatic listing
- **Release Notes** - Mentioned in changelog
- **Website** - Contributors page (planned)
- **Discord Role** - Special contributor role

---

## 📞 Getting Help

Need help contributing?

1. **Read this guide** - Most questions are answered here
2. **Check documentation** - https://kckonicki.github.io/HySuite/
3. **Search issues** - Someone may have asked already
4. **Ask on Discord** - Community is friendly and helpful
5. **Open an issue** - Tag it with `question`

---

## 🎯 First Time Contributors

New to open source? We've got you covered!

### Good First Issues

Look for issues labeled `good first issue` - these are:
- Well documented
- Relatively simple
- Great for learning
- Have mentorship available

### Learning Resources

- [GitHub Flow](https://guides.github.com/introduction/flow/)
- [Markdown Guide](https://www.markdownguide.org/)
- [Java Style Guide](https://google.github.io/styleguide/javaguide.html)
- [Maven Basics](https://maven.apache.org/guides/getting-started/)

### Mentorship

First-time contributors get:
- Extra patience and support
- Detailed code review feedback
- Help with Git and GitHub
- Guidance on best practices

Don't be afraid to ask questions! Everyone was a beginner once. 🌱

---

## 📄 License

By contributing to HySuite, you agree that your contributions will be licensed under the MIT License.

---

## 🙏 Thank You

Every contribution, no matter how small, makes HySuite better. Whether you:
- Report a bug
- Fix a typo
- Add a feature
- Help someone on Discord
- Share your server setup

**You're making a difference!** Thank you for being part of the HySuite community. 💜

---

**Questions?** Open an issue or ask on Discord!

**Ready to contribute?** Fork the repo and start coding! 🚀