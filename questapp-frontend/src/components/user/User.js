import React from "react";
import { useParams } from "react-router-dom";

function User() {
  const { userId } = useParams();

  return (
    <div>
      <h1>Useerr!!!{userId}</h1>
    </div>
  );
}
export default User;
