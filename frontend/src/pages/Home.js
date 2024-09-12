import React, { useEffect, useState } from "react";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faEye,
  faEdit,
  faTrash,
  faPlus,
  faStar,
} from "@fortawesome/free-solid-svg-icons";
import { Link, useParams } from "react-router-dom";

export default function Home() {
  const [students, setStudents] = useState([]);
  const { studentId } = useParams();

  useEffect(() => {
    loadStudents();
    console.log("React Tutorial");
  }, []);

  const loadStudents = async () => {
    try {
      const result = await axios.get("http://localhost:8080/api/students");
      setStudents(result.data);
      console.log(result.data);
    } catch (error) {
      console.error("Error loading students:", error);
    }
  };

  const deleteStudent = async (id) => {
    await axios.delete(`http://localhost:8080/api/students/${id}`);
    loadStudents();
  };

  return (
    <div className="container">
      <div className="py-4">
        <div className="table-responsive">
          <table className="table border shadow">
            <thead className="table-header">
              <tr>
                <th scope="col">#</th>
                <th scope="col">First Name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Username</th>
                <th scope="col">Email</th>
                <th scope="col">Password</th>
                <th scope="col">Age</th>
                <th scope="col">Category</th>
                <th scope="col">Reviews</th>
                <th scope="col">Actions</th>
              </tr>
            </thead>
            <tbody>
              {students.map((student, index) => (
                <tr key={index} className={index % 2 === 0 ? "even-row" : ""}>
                  <th scope="row">{index + 1}</th>
                  <td>{student.firstName}</td>
                  <td>{student.lastName}</td>
                  <td>{student.username}</td>
                  <td>{student.email}</td>
                  <td>{student.password}</td>
                  <td>{student.age}</td>
                  <td>{student.categoryName}</td>
                  <td>
                    {student.reviewDtoList &&
                    student.reviewDtoList.length > 0 ? (
                      student.reviewDtoList.map((review, reviewIndex) => (
                        <div key={reviewIndex}>
                          <strong>{review.name}:</strong>{" "}
                          <span>{review.description}</span>
                        </div>
                      ))
                    ) : (
                      <div>No reviews available</div>
                    )}
                  </td>
                  <td>
                    <Link className="btn btn-primary mx-2" to={`/view/${student.id}`}style={{backgroundColor: "#9400D3", borderColor: "#9400D3",}} >
                      <FontAwesomeIcon icon={faEye} /> View
                    </Link>
                    <Link className="btn btn-outline-primary mx-2"style={{ color: "#9400D3", borderColor: "#9400D3" }}to={`/${student.id}`}>
                      <FontAwesomeIcon icon={faEdit} /> Edit
                    </Link>
                    <button className="btn btn-primary mx-2" style={{backgroundColor: "#9400D3", borderColor: "#9400D3", }}onClick={() => deleteStudent(student.id)}>
                    <FontAwesomeIcon icon={faTrash} />
                  </button>
                    <Link className="btn btn-outline-primary mx-2" style={{ color: "#9400D3", borderColor: "#9400D3" }} to={`/save/${student.id}`}>
                      <FontAwesomeIcon icon={faStar} /> Review
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
