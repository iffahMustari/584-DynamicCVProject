# Dynamic Online CV Builder

**CSC584 Enterprise Programming | Individual Assignment (Semester 1 2026/2027)**
Universiti Teknologi MARA, Cawangan Terengganu

A Java EE web application that lets users create and generate a personal CV dynamically. The project follows the **Model-View-Controller (MVC)** architecture, with the data, logic and presentation separated into their own layers.

## Features

**Required by the assignment**
- Generate a CV from form input (HTTP POST)
- Preview a sample CV with pre-filled data (HTTP GET, `?action=sample`)
- Server-side validation inside the bean
- Error page that lists the problems and links back to the form
- Servlet and welcome page configured in `web.xml`
- Clean, print-friendly CV layout

**Additional features (extra explored)**
- **Multiple Education and Experience entries** with add/remove buttons
- **Location** field for each experience
- **Additional coursework** for each education (CGPA, Dean's List, relevant courses)
- **Form data kept** when the user goes back to correct mistakes (saved in session)
- **No-cache filter** so the Back button reloads the form with the saved data
- **Colour themes**: Black & White and Dark Blue, preserved when printing

## MVC Structure

| Layer | Components | Responsibility |
|---|---|---|
| **Model** | `CVBean`, `Education`, `Experience` | Stores CV data and validation rules |
| **View** | `cvForm.jsp`, `cvView.jsp`, `cvError.jsp`, `style.css` | Input form, CV display, error display |
| **Controller** | `CVServlet`, `NoCacheFilter` | Handles requests, populates the model, forwards to views |

**Request flow**
1. The browser submits the form (POST) or requests the sample CV (GET).
2. `CVServlet` receives the request.
3. The controller populates and validates `CVBean`.
4. The controller stores the bean with `request.setAttribute()` and forwards using `RequestDispatcher`.
5. The JSP renders the HTML and sends it back to the browser.

## Project Structure

```
584-DynamicCVProject/
├── pom.xml
└── src/main/
    ├── java/com/cvbuilder/
    │   ├── controller/
    │   │   ├── CVServlet.java
    │   │   └── NoCacheFilter.java
    │   └── model/
    │       ├── CVBean.java
    │       ├── Education.java
    │       └── Experience.java
    └── webapp/
        ├── WEB-INF/web.xml
        ├── css/style.css
        ├── cvForm.jsp
        ├── cvView.jsp
        └── cvError.jsp
```

## Tech Stack

- Java 11+ (built with JDK 21)
- Servlet, JSP, JSTL (Jakarta EE)
- Maven (WAR packaging)
- GlassFish Server
- Apache NetBeans

## How to Run

1. Clone the repository
   ```bash
   git clone https://github.com/iffahMustari/584-DynamicCVProject.git
   ```
2. Open the project in **NetBeans** (File → Open Project)
3. Right-click the project → **Clean and Build**
4. Right-click the project → **Run** (select **GlassFish Server**)
5. Open `http://localhost:8080/DynamicCVProject/`

> The project uses `jakarta.*` packages, so it needs a Jakarta EE server (GlassFish 6+/Tomcat 10+).

## Usage

- Click **Preview Sample CV** to see a ready-made CV.
- Or fill in the form and click **Generate CV**.
- Mandatory fields: Full Name, Email, at least one Education (with Degree), and at least one Skill.
- On the CV page, choose a colour theme and click **Print CV**.
- Click **Back to Form (Edit)** to correct your data without typing everything again.


## Author

**Iffah Mustari**
Computer Science, Universiti Teknologi MARA
GitHub: [@iffahMustari](https://github.com/iffahMustari)
