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
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import axios from "axios";
import { Link, useNavigate, useParams } from "react-router-dom";
import React, { useState, useEffect } from "react";

export default function EditStudent() {
  let navigate = useNavigate();

  const {studentId} = useParams()

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


    useEffect(()=>{
      loadStudents();
    }, []);

  const onSubmit = async (event) => {
    event.preventDefault();
    await axios.put(`http://localhost:8080/api/students/${studentId}`, studentDto);
    navigate("/");
  };

  const loadStudents = async ()=>{
    const result = await axios.get(`http://localhost:8080/api/students/view/${studentId}`)
    setStudentDto(result.data)
  }

  const [categories, setCategories] = useState([]); // Define categories state

  useEffect(() => {
    // Fetch categories when the component mounts
    axios
      .get("http://localhost:8080/api/categories/findAll")
      .then((response) => {
        setCategories(response.data); // Assuming the categories are returned in an array format
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
            <FontAwesomeIcon icon={faEdit} className="me-2" />
            Edit
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
                placeholder="Enter your age"
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
