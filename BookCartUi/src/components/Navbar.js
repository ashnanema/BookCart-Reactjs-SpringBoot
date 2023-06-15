import { Button, Navbar ,Container ,Nav  ,NavDropdown} from 'react-bootstrap'
import { Link } from "react-router-dom";

const NavbarTop = () => {
    return ( 
        <div >
           <Navbar bg="navbar navbar-dark bg-dark" expand="lg" className="sticky-top">
  <Container>
    <Navbar.Brand  to="/home" >BookKart</Navbar.Brand>
    <Navbar.Toggle aria-controls="basic-navbar-nav" />
    <Navbar.Collapse id="basic-navbar-nav">
      <Nav className="me-auto">
        <Link className="nav-link" to="/">Home</Link>
        {/* <Link className="nav-link" to="/mediacoverage">Media Coverage</Link> */}
        
       
      </Nav>
    </Navbar.Collapse>
  </Container>
</Navbar>
        </div>
     );
}
 
export default NavbarTop;

