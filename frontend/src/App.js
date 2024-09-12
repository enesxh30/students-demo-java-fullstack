
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from './layout/Navbar';
import Home from './pages/Home';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddStudent from './students/AddStudent';
import EditStudent from './students/EditStudent';
import ViewStudent from './students/ViewStudent';
import AddReview from './students/AddReview';
import AddCategory from './students/AddCategory';
import Footer from './layout/Footer';


function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <div className="content">
        <Routes>
          <Route exact path="/" element={<Home/>} />
          <Route exact path="/save" element={<AddStudent/>} />
          <Route exact path="/view/:id" element={<ViewStudent/>} />
          <Route exact path="/:studentId" element={<EditStudent/>} />
          <Route exact path="/save/:studentId" element={<AddReview/>} />
          <Route exact path="/saveCategory" element={<AddCategory/>} />
        </Routes>
        </div>
        <Footer className="footer" />
      </Router>
    </div>
  );
}


export default App;
