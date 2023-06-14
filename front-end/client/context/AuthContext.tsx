import { createContext, useState, useEffect, ReactNode } from "react";
import axios from "axios";
import { User, loginSignUpObject, tokenData, IAuthContext } from "../interfaces";

type AuthProviderProps = {
    children: ReactNode
}

const AuthContext = createContext<IAuthContext | null>(null);

export default AuthContext;

export const AuthProvider = ({ children } : AuthProviderProps) => {
  const [user, setUser] = useState<User | null>(null);
  const [error, setError] = useState<string | null>(null);
  
  useEffect(() => {
    const token: string | null = window.localStorage.getItem("token");


    // if (token) {
    //   const authenticate = async (token: string) => {
    //     const { data } = await axios({
    //       method: "get",
    //       url: "http://localhost:8080/api/user",
    //       headers: { Authorization: token },
    //     });
    //     setUser(data);
    //   };
    //   authenticate(token);
    // }
  }, []);

  const loginSignUp = async (loginSignupObj: loginSignUpObject) => {
    let tokenData : tokenData  

    if(loginSignupObj.method === "login"){
      console.log("HIT RIGHT ROUTE", loginSignupObj)
        delete loginSignupObj.method
        const { data }  = await axios.post(`http://localhost:8080/api/user/verify/authentication`, loginSignupObj);
        tokenData = data
        console.log(data, "THISI SDATA")
    } else {
        delete loginSignupObj.method
        const { data }  = await axios.post(`http://localhost:8080/api/user/verify/register`, loginSignupObj);
        tokenData = data
    }

    if (typeof tokenData.token === "string") {
      return setError(tokenData.token);
    } else {
      window.localStorage.setItem("token", tokenData.token);
      const userData = await axios({
        method: "get",
        url: "http://localhost:8080/api/user",
        headers: { Authorization: tokenData.token },
      });
      setUser(userData.data);
    }
  };

  const logOut = () => {
    setUser(null);
    window.localStorage.removeItem("token")
  };

  let context: IAuthContext = { loginSignUp, logOut, user, error };
  
  return (
    <AuthContext.Provider value={context}>{children}</AuthContext.Provider>
  );
};
