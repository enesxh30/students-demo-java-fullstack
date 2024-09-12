import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUser,
  faUserTag,
  faCalendar,
  faUserAlt,
  faEnvelope,
  faLock,
  faCheck,
  faTags,
  faTimes,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import React, { useState, useEffect } from "react";

export default function AddStudent() {
  let navigate = useNavigate();

  const [studentDto, setStudentDto] = useState({
    firstName: "",
    lastName: "",
    age: 0,
    username: "",
    email: "",
    password: "",
    categoryId: 0,
  });

  const onInputChange = (event) => {
    const { name, value } = event.target;
    setStudentDto({ ...studentDto, [name]: value });
  };

  const { firstName, lastName, age, username, email, password, categoryId } =
    studentDto;

    const onSubmit = async (event) => {
      event.preventDefault();
      
      const authToken = 'yourAuthToken';
    
      try {
        await axios.post("http://localhost:8080/api/students/save", studentDto, {
          headers: {
            Authorization: `Bearer ${authToken}`,
            'Content-Type': 'application/json',
          },
        });
        navigate("/");
      } catch (error) {
        console.error("Error adding student:", error);
        
      }
    };

  const [categories, setCategories] = useState([]); 

  useEffect(() => {
   
    axios
      .get("http://localhost:8080/api/categories/findAll")
      .then((response) => {
        setCategories(response.data); 
      })
      .catch((error) => {
        console.error("Error fetching categories:", error);
      });
  }, []);
  
  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2
            className="text-center mb-4"
            style={{
              color: "#9400D3",
              fontSize: "2.5rem",
              fontFamily: "Arial, sans-serif",
            }}
          >
             Student Form
          </h2>
          <form onSubmit={(event) => onSubmit(event)}>
            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="First Name" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faUser}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow custom-input"
                placeholder="John"
                name="firstName"
                value={firstName}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Last Name" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faUserTag}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow"
                placeholder="Smith"
                name="lastName"
                value={lastName}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Age" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faCalendar}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="number"
                className="form-control with-shadow"
                placeholder="Age like ....18"
                name="age"
                value={age}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Username" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faUserAlt}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow"
                placeholder="Nickname or username"
                name="username"
                value={username}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Email" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faEnvelope}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="text"
                className="form-control with-shadow"
                placeholder="someone@email.com"
                name="email"
                value={email}
                onChange={(event) => onInputChange(event)}
              />
            </div>
            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Password" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faLock}
                  className="mr-2"
                  style={{ color: "#9400D3" }}
                />
              </label>
              <input
                type="password"
                className="form-control with-shadow"
                placeholder="Enter your password"
                name="password"
                value={password}
                onChange={(event) => onInputChange(event)}
              />
            </div>

            <div className="mb-3 d-flex align-items-center">
              <label htmlFor="Category" className="form-label with-shadow">
                <FontAwesomeIcon
                  icon={faTags}
                  className="mr-2"
                  
                  style={{ color: "#9400D3" }}
                />
              </label>
              <select
                className="form-control with-shadow"
                name="categoryId" // Update name attribute to match the state key
                value={categoryId}
                onChange={(event) => onInputChange(event)}
              >
                <option value="">Select category...</option>
                {categories.map((categoryItem) => (
                  <option key={categoryItem.id} value={categoryItem.id}>
                    {categoryItem.name}{" "}
                    {/* Display category name instead of ID */}
                  </option>
                ))}
              </select>
            </div>
            <button  className="btn btn-primary mx-2"style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }}>
              <FontAwesomeIcon icon={faCheck} />
            </button>

            <Link className="btn btn-primary mx-2"style={{ backgroundColor: "#9400D3", borderColor: "#9400D3" }} to="/">
              <FontAwesomeIcon icon={faTimes} />
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
