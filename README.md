# Kotlin HTML Builder Kata

## Background

In this kata, you will create a simple HTML builder using Kotlin. The goal is to develop a terminal application that generates an HTML page from content provided in text files. This exercise will help you practice file I/O operations, string manipulation, and basic HTML structure creation in Kotlin.

## Task Description

Your task is to create a Kotlin application that does the following:

1. Accepts multiple file paths as command-line arguments.
2. Reads the content of each provided file.
3. Generates a single HTML page where:
   - Each file's content is placed in a separate paragraph (`<p>` tag).
   - The page has a proper HTML structure with `<html>`, `<head>`, and `<body>` tags.
4. Saves the generated HTML to an `output.html` file.

## Requirements

1. The application should be a command-line tool.
2. Use Kotlin to implement the solution.
3. Implement proper error handling for file operations.
4. The generated HTML should be well-formed and valid.
5. Include appropriate unit tests for your code.

## Example Usage

```bash
kotlin HtmlBuilderKt input1.txt input2.txt input3.txt
```

This command should generate an `output.html` file with content from the input files.

## Expected Output Structure

The generated `output.html` should have a structure similar to this:

```html
<!DOCTYPE html>
<html>
<head>
    <title>Generated HTML</title>
</head>
<body>
    <p>Content from input1.txt</p>
    <p>Content from input2.txt</p>
    <p>Content from input3.txt</p>
</body>
</html>
```

## Bonus Challenges

1. Add basic CSS styling to the generated HTML.
2. Implement a feature to specify the output file name.
3. Add support for different HTML tags based on file extensions (e.g., `.md` for `<blockquote>`, `.code` for `<pre><code>`).

## Evaluation Criteria

- Correct functionality according to the requirements
- Code quality and organization
- Proper error handling
- Effective use of Kotlin features
- Quality and coverage of unit tests

## Setup

1. Create a new Kotlin project.
2. Set up a command-line application structure.
3. Implement the HTML builder logic.
4. Write unit tests to verify your implementation.
5. Ensure the application runs correctly with various input files.

Good luck, and happy coding!