import logo from './logo.svg';
import './App.css';
import ListOfBooks from './components/ListOfBooks';
import Navbar from './components/Navbar';

import MediaCoverage from './components/MediaCoverage';
import {
  BrowserRouter,
  Routes,
  Route,
} from "react-router-dom";

import Testing from './components/Testing'

function App() {
  return (
    <div className="App">
      <Navbar/>

      <Routes>
     {/* <ListOfBooks/> */}
     <Route path="/" element={<ListOfBooks />} />
     <Route path="home" element={<ListOfBooks />} />
      <Route path="mediacoverage" element={<MediaCoverage />} />
      <Route path="books:buy" element={<Testing />} />

      </Routes>
    </div>
  );
}

export default App;
