# Remote Printing System

The Remote Printing System is a simple Java-based application that enables users to submit print jobs to a remote server. Users can register and log in to the system, then send print jobs that will be processed by the server. The server assigns a unique job ID for each print job and manages them in a queue.

## Features

- User registration and login with secure password storage using BCrypt and MongoDB.
- Print job submission from clients to a remote server.
- Unique job ID assignment for each submitted print job.

## Components

The Remote Printing System consists of the following components:

1. `PrintServer`: A server-side application that listens for incoming print job requests, processes them, and manages the print job queue.
2. `PrintClient`: A client-side application that allows users to register, log in, and submit print jobs to the remote server.
3. `PrintJob`: A class that represents a print job with a unique ID and content.
4. `Authentication`: A class that handles user registration and authentication using MongoDB for secure data storage.

## Setup

### Prerequisites

- Java Development Kit (JDK) version 8 or later.
- MongoDB server installed and running on the default port (27017).

### Dependencies

- [MongoDB Java Driver](https://mongodb.github.io/mongo-java-driver/): A Java driver for MongoDB that allows the application to connect and interact with the database.
- [jBCrypt](https://www.mindrot.org/projects/jBCrypt/): A Java library for hashing passwords using the BCrypt algorithm.

### Running the Application

1. Clone this repository to your local machine.

```bash
git clone https://github.com/your_username/remote-printing-system.git
  ```
  
2. Change to the project directory.
```bash
cd remote-printing-system
```
3. Compile the source code.
```bash
javac -classpath "path/to/mongo-java-driver.jar:path/to/jbcrypt.jar" *.java
```
4. Run the PrintSystem class to start both the server and client.
```bash
java -classpath ".:path/to/mongo-java-driver.jar:path/to/jbcrypt.jar" PrintSystem
```

`Note`: In a real-world scenario, you would typically have the server and client running on separate machines. You would run the PrintServer on the server-side and the PrintClient on each client machine that needs to access the remote printing system.

## Usage
After starting the `PrintClient`, you will be presented with the following options:

1. Register: Create a new user account with a unique username and password.
2. Login: Log in to the system using an existing username and password.
3. Exit: Close the client application.
After successfully logging in, you can submit print jobs by entering the content at the command line. The server will respond with a job ID for each submitted print job. To disconnect from the server, type 'exit'.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
