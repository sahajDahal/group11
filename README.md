# TimeGrid - Task Manager

TimeGrid is a simple web-based task management application that allows users to create, edit, and track their tasks with a progress indicator.

## Features

- User authentication (login/signup)
- Create and manage tasks
- Track task progress with a slider
- Edit task details
- Delete completed tasks
- Responsive design for mobile and desktop
- Local storage for task persistence

## Prerequisites

- Python 3.x
- pip (Python package installer)

## Installation

1. Clone this repository:
```bash
git clone <repository-url>
cd <repository-directory>
```

2. Install the required dependencies:
```bash
pip install -r requirements.txt
```

## Running the Application

1. Navigate to the frontend directory:
```bash
cd src/frontend
```

2. Start the Flask server:
```bash
python3 server.py
```

3. Open your web browser and go to:
   - http://127.0.0.1:8000/login.html

## Test Accounts

You can use any of these test accounts to log in:

- Username: `testuser`, Password: `password123`
- Username: `admin`, Password: `admin123`
- Username: `johndoe`, Password: `doe123`
- Username: `random`, Password: `random123`

## Usage

1. Log in using one of the test accounts above
2. Create a new task by clicking the "+ Create Task" button
3. Fill in the task details:
   - Title (required)
   - Description (optional)
   - Initial progress (0-100%)
4. Use the progress slider to update task completion
5. Edit tasks using the pencil icon
6. Delete tasks using the X button
7. Tasks automatically disappear when progress reaches 100%

