import { Toast,Row,Col,Button,ToastContainer} from "react-bootstrap";
import { useState } from "react";
const NotifiactionToast = (props) => {

  const [show, setShow] = useState(true);
  const handleclose=()=>{
    // setShow(false)
    props.setmessage(null);
  }
    return ( 
    <ToastContainer position="top-end" className="p-3 sticky-top">
    <Row  >
      <Col >
        <Toast onClose={handleclose} show={show} delay={6000} autohide style={{backgroundColor:props.message ==="Books are bought successfully"?"rgb(78, 245, 66)":"rgb(194, 23, 14)"}}>
          <Toast.Header>
            
            <strong className="me-auto">{props.message}</strong>
          </Toast.Header>
         
        </Toast>
      </Col>
    </Row>
    </ToastContainer>
     );
}
 
export default NotifiactionToast;