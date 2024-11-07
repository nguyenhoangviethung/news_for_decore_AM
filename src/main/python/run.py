import subprocess
import json
from flask import Flask, jsonify
def run_java():
    try:
        classPath = "target/classes;target/dependency/*"

        result = subprocess.run(['java', '-cp', classPath, 'com.apartment.Main'], capture_output=True, text=True, check=True)
        print("Output from Java program:")
        print(result.stdout)
    except subprocess.CalledProcessError as e:
        print(f"Error running Java program: {e}")
        print(f"stderr: {e.stderr}")
    except FileNotFoundError:
        print("Java is not installed or not found in the system path.")

def create_app():
    app = Flask(__name__)
    @app.route('/get-data')
    def getJson():
        run_java()
        with open('src\\main\\tmp\\data.json','r', encoding = 'utf_8') as f:
            data = json.load(f)
            return jsonify(data)
    return app

if __name__ == "__main__":
    app = create_app()
    app.run(debug=True)

