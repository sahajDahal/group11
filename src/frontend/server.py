from flask import Flask, request, jsonify, send_from_directory
from flask_cors import CORS
import csv
import os

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes

# Serve static files
@app.route('/')
def index():
    return send_from_directory('.', 'index.html')

@app.route('/<path:path>')
def serve_file(path):
    return send_from_directory('.', path)

# Handle user registration
@app.route('/saveUser', methods=['POST'])
def save_user():
    data = request.json
    
    # Check if user already exists
    with open('data/users.csv', 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            if row['username'] == data['username']:
                return jsonify({'error': 'Username already exists'}), 400
            if row['email'] == data['email']:
                return jsonify({'error': 'Email already exists'}), 400
    
    # Save new user
    with open('data/users.csv', 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([data['email'], data['username'], data['password']])
        file.write('\n')  # Add an extra newline for safety
    
    return jsonify({'message': 'User registered successfully'}), 200

# Handle login
@app.route('/login', methods=['POST'])
def login():
    data = request.json
    print(f"Login attempt - Username: {data['username']}")  # Debug log
    
    with open('data/users.csv', 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            print(f"Checking against - Username: {row['username']}, Password: {row['password']}")  # Debug log
            if row['username'] == data['username'] and row['password'] == data['password']:
                print("Login successful!")  # Debug log
                return jsonify({'message': 'Login successful'}), 200
    
    print("Login failed - Invalid credentials")  # Debug log
    return jsonify({'error': 'Invalid username or password'}), 401

if __name__ == '__main__':
    # Create data directory if it doesn't exist
    os.makedirs('data', exist_ok=True)
    
    # Create users.csv if it doesn't exist
    if not os.path.exists('data/users.csv'):
        with open('data/users.csv', 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['email', 'username', 'password'])
    
    app.run(port=8000) 