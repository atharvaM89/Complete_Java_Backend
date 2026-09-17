// DOM Elements
const askBtn = document.getElementById("askBtn");
const questionInput = document.getElementById("question");
const answerBox = document.getElementById("answer");
const themeToggle = document.getElementById("themeToggle");
const loader = document.getElementById("loader");
const clearBtn = document.getElementById("clearBtn");
const copyBtn = document.getElementById("copyBtn");
const sampleQuestionBtn = document.getElementById("sampleQuestionBtn");
const fullscreenBtn = document.getElementById("fullscreenBtn");
const responseBox = document.getElementById("responseBox");

// Sample questions for the seminar
const sampleQuestions = [
  "What are the key benefits of using Spring AI with JavaScript?",
  "How does Spring AI compare to other AI frameworks?",
  "Can you explain the architecture of a typical Spring AI application?",
  "What are best practices for implementing AI in enterprise applications?",
  "How can we optimize performance when working with large language models?",
];

// Check for saved theme preference or default to light
const savedTheme = localStorage.getItem("theme") || "light";
if (savedTheme === "dark") {
  document.body.classList.add("dark-mode");
  themeToggle.innerHTML = '<i class="fas fa-sun"></i>';
}

// Main function to ask the AI
async function askAI() {
  const question = questionInput.value.trim();
  if (!question) {
    answerBox.textContent = "Please type something first!";
    return;
  }

  // Show loading animation
  answerBox.textContent = "";
  loader.style.display = "block";
  responseBox.classList.add("loading");

  try {
    const res = await fetch("http://localhost:8080/api/chat", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ question }),
    });

    if (!res.ok) {
      throw new Error("Server error");
    }

    const data = await res.json();

    // Format code blocks if any
    const formattedAnswer = formatCodeBlocks(data.answer);
    answerBox.innerHTML = formattedAnswer;
  } catch (error) {
    console.error(error);
    answerBox.innerHTML =
      "<span style='color: #e74c3c;'><i class='fas fa-exclamation-triangle'></i> Failed to connect to AI backend. Please check if the server is running.</span>";
  } finally {
    // Hide loading animation
    loader.style.display = "none";
    responseBox.classList.remove("loading");
  }
}

// Format code blocks in the response
function formatCodeBlocks(text) {
  // Simple formatter for code blocks that start with ```
  return text.replace(/```([\s\S]*?)```/g, '<div class="code-block">$1</div>');
}

// Event Listeners
askBtn.addEventListener("click", askAI);

// Allow pressing Enter to submit
questionInput.addEventListener("keydown", (e) => {
  if (e.key === "Enter" && !e.shiftKey) {
    e.preventDefault();
    askAI();
  }
});

// Toggle dark/light mode
themeToggle.addEventListener("click", () => {
  document.body.classList.toggle("dark-mode");

  const isDark = document.body.classList.contains("dark-mode");
  localStorage.setItem("theme", isDark ? "dark" : "light");

  // Update icon
  themeToggle.innerHTML = isDark
    ? '<i class="fas fa-sun"></i>'
    : '<i class="fas fa-moon"></i>';
});

// Clear the input and response
clearBtn.addEventListener("click", () => {
  questionInput.value = "";
  answerBox.textContent = "I'm ready to help with your questions!";
  questionInput.focus();
});

// Copy response to clipboard
copyBtn.addEventListener("click", () => {
  const text = answerBox.textContent;
  navigator.clipboard.writeText(text).then(
    () => {
      // Temporary visual feedback
      copyBtn.innerHTML = '<i class="fas fa-check"></i> Copied!';
      setTimeout(() => {
        copyBtn.innerHTML = '<i class="fas fa-copy"></i> Copy Response';
      }, 2000);
    },
    (err) => {
      console.error("Could not copy text: ", err);
    }
  );
});

// Load a random sample question
sampleQuestionBtn.addEventListener("click", () => {
  const randomIndex = Math.floor(Math.random() * sampleQuestions.length);
  questionInput.value = sampleQuestions[randomIndex];
  questionInput.focus();
});

// Toggle fullscreen for presentation mode
fullscreenBtn.addEventListener("click", () => {
  if (!document.fullscreenElement) {
    document.documentElement.requestFullscreen().catch((e) => {
      console.error(`Error attempting to enable fullscreen: ${e.message}`);
    });
    fullscreenBtn.innerHTML = '<i class="fas fa-compress"></i> Exit Fullscreen';
  } else {
    if (document.exitFullscreen) {
      document.exitFullscreen();
      fullscreenBtn.innerHTML =
        '<i class="fas fa-expand"></i> Presentation Mode';
    }
  }
});
