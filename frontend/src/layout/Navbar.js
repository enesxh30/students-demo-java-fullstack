import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faUserPlus, faListAlt,faPlus, faGraduationCap} from "@fortawesome/free-solid-svg-icons";
import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <div>
      <nav className="navbar navbar-expand-lg navbar-dark" style={{ backgroundColor: "#9400D3" }}>
        <div className="container-fluid justify-content-center">
          <div className="d-flex align-items-center">
            <Link className="btn btn-outline-light me-3" to="/saveCategory">
              <FontAwesomeIcon icon={faListAlt} className="me-2" />
              Add Category
            </Link>
          </div>
          <Link className="navbar-brand mx-auto" href="#" style={{ fontFamily: "Arial, sans-serif" }}>
          <FontAwesomeIcon icon={faGraduationCap} className="me-2" />
            Student Application
          </Link>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
          <Link className="btn btn-outline-light" to="/save">
            <FontAwesomeIcon icon={faUserPlus} className="me-2" /> Add Student
          </Link>
        </div>
      </nav>
    </div>
  );
}

