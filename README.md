🚀 AI API Debugger

An AI-powered tool that analyzes error logs and instantly suggests root causes and possible fixes.

🎯 Features
Paste error logs and get instant analysis
Detects root causes using AI
Suggests actionable fixes
Severity classification (LOW / MEDIUM / HIGH)
Clean and simple UI with real-time feedback
🧠 Tech Stack
Backend: Java Spring Boot
Frontend: Angular
AI: Google Gemini
Containerization: Docker
⚙️ Getting Started
1️⃣ Clone the repository
git clone https://github.com/senturkhasan/ai-api-debugger.git
cd ai-api-debugger
2️⃣ Configure API Key

Create a file named application.yml in the backend project and add your Gemini API key:

gemini:
  api:
    key: YOUR_API_KEY_HERE

Alternatively, you can use environment variables:

export GEMINI_API_KEY=your_api_key
3️⃣ Run Backend
cd backend
mvn spring-boot:run
4️⃣ Run Frontend
cd frontend
npm install
ng serve
🐳 Run with Docker (Optional)
docker compose up --build
🧪 Example

Input:

NullPointerException at UserService line 45

Output:

{
  "rootCause": "User object is null",
  "fix": "Initialize object or check for null",
  "severity": "HIGH"
}
💡 Motivation

Debugging logs can be time-consuming and repetitive.
This project aims to speed up the process by leveraging AI to provide quick insights and solutions.
 

🔐 Security Note

API keys are not included in the repository.
Please configure your own key locally using environment variables or configuration files.