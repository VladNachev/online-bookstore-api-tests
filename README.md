[![CI](https://github.com/VladNachev/online-bookstore-api-tests/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/VladNachev/online-bookstore-api-tests/actions/workflows/ci.yml)

[![Allure Report](https://img.shields.io/badge/Allure_Report-View_Report-1f6feb?logo=allure&logoColor=white)](https://vladnachev.github.io/online-bookstore-api-tests/)

# Online Bookstore API Test Automation Framework

!!! Project still under constructions !!!

## CI and Allure Reports

This project uses GitHub Actions to run the API test suite automatically on every push and pull request to the `master` branch.

The CI workflow performs the following steps:

- Checks out the repository
- Sets up Java 17
- Runs the Maven TestNG test suite
- Generates an Allure report
- Uploads the Allure report as a downloadable GitHub Actions artifact
- Publishes the latest Allure report to GitHub Pages

### GitHub Pages Report

The latest published Allure report can be viewed here:

[Allure Report](https://vladnachev.github.io/online-bookstore-api-tests/)

This report is updated after every successful push workflow run on the `master` branch.

### Downloading the Allure Report Artifact

Each workflow run also stores the generated Allure report as a downloadable artifact.

To download it:

1. Open the repository on GitHub.
2. Go to the **Actions** tab.
3. Select the latest workflow run.
4. Scroll down to the **Artifacts** section.
5. Download the `allure-report` artifact.
6. Extract the downloaded `.zip` file.
7. Open the report through a local web server, for example:
```bash
cd allure-report
python -m http.server 8080
