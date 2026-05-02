# 🚀 AI API Debugger

An AI-powered tool that analyzes error logs and instantly suggests root causes and possible fixes.

---

## 🎯 Features

- 📌 Paste error logs and get instant analysis  
- 🧠 Detect root causes using AI  
- 🛠 Suggest actionable fixes  
- 🚦 Severity classification (LOW / MEDIUM / HIGH)  
- ⚡ Clean and simple UI with real-time feedback  

---

## 🧠 Tech Stack

- **Backend:** Java Spring Boot  
- **Frontend:** Angular  
- **AI:** Google Gemini  
- **Containerization:** Docker  

---

## ⚙️ Getting Started

### 1️⃣ Clone the repository

```bash
git clone https://github.com/senturkhasan/ai-api-debugger.git
cd ai-api-debugger

2️⃣ Configure API Key

Create application.yml inside the backend:

gemini:
  api:
    key: YOUR_API_KEY_HERE

Or use environment variables:

export GEMINI_API_KEY=your_api_key

3️⃣ Run Backend
cd backend
mvn spring-boot:run

4️⃣ Run Frontend
cd frontend
npm install
ng serve
🐳 Run with Docker
docker compose up --build
🧪 Example
Input
NullPointerException at UserService line 45
Output
{
  "rootCause": "User object is null",
  "fix": "Initialize object or check for null",
  "severity": "HIGH"
}
💡 Motivation

Debugging logs is often repetitive and time-consuming.
This project helps developers quickly understand errors and find solutions using AI.
