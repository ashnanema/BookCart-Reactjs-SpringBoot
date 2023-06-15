const Error = ({error}) => {
    return ( 
        <div className="container">
        <div className="alert alert-danger" role="alert">
  {error}
</div>
        </div>
     );
}
 
export default Error;