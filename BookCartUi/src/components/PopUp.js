import '../styles/props.css'
import { useState } from "react";
import BuyService from '../services/BuyService';
import NotifiactionToast from './NotifiactionToast';
import { useNavigate } from "react-router-dom";

const PopUp = (props) => {
  const[copies ,setcopies] = useState('');
  const[responseMessage, setresponseMesage] = useState(null);
  const isbnNo = props.content.isbnNo;
  let navigate = useNavigate(); 

  const handlebuying = (e) =>{
    e.preventDefault();
    const buyRequest = {isbnNo, copies};
    BuyService.buyBook(buyRequest).then(data=>props.setmessage(data))
    .catch(err=>props.setmessage(err.response.data.message));
    // let path = `/`; 
    // navigate(path);
    // this.setState({redirect: true});
    // window.location.reload(false);
    
    props.handleClose();
  }
      return ( 
      <>
    
        <div className="popup-box"  >
        <div className="box" style={{backgroundColor:"#6c757d"}} >
          <span className="close-icon" onClick={props.handleClose}>x</span>
          <div className="card text-center" style={{width:"100%"}}>
  <div className="card-header">
     Title : {props.content.title}
  </div>
  <div className="card-body">
    <h5 className="card-title">Author : {props.content.author}</h5>
    <p className="card-text">Price : {props.content.price}</p>
    <form className="card-text" >
        <label> copies</label>
        <br />
        <select  onChange={(e) => setcopies(parseInt(e.target.value))}>
        <option value='1'>1</option>
        <option value='2'>2</option>
        <option value='3'>3</option>
        <option value='4'>4</option>
        <option value='5'>5</option>
        <option value='6'>6</option>
        <option value='7'>7</option>
        <option value='8'>8</option>
        <option value='9'>9</option>
        <option value='10'>10</option>
        </select>
        <br></br>
  </form>
  </div>
  <div className="card-footer text-muted">
   <button onClick={handlebuying} className='btn btn-success'>Buy Now</button>
  </div>
 
</div>
      </div>
      </div>
      </>
     );
}
 
export default PopUp;