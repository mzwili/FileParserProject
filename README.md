# ThuthukaniMthiyane FileParserProject

[![Java Version](https://img.shields.io/badge/Java-17-blue)](#)
[![Spring Boot Version](https://img.shields.io/badge/Spring%20Boot-2.7.13-green)](#)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

FileParserProject is a flat file data storage project built with Java and Spring Boot. It provides functionality to parse CSV files, convert CSV data to images, and create image links.
It stores the links to the images on the H2 Database along with the name and surname data extracted from csv file located in the resources directory.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Clone the repository:

   ```bash
   git clone <repository_url>
   ```

2. Build the project using Maven:

   ```bash
   mvn clean install
   ```

## Usage

To run the application, execute the following command:


```bash
cd FileParserProject-main
mvn spring-boot:run
```

## Endpoints

The following endpoints are available:

- `GET /v1/api/image/{name}/{surname}/{\\w.\\w}` - Retrieves the HTTP image link for the specified user.

## Urls

The following links are for viewing your database data and picture on the browser.

The H2 Database:

```bash
http://localhost:8080/h2-console/
```

Image rendering (just replace name and  surname values found on database and add an alphanumeric charactor):

```bash
http://localhost:8080/v1/api/image/{name}/{surname}/{\\w.\\w}
```

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please feel free to open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
