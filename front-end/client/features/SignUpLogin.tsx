import { ReactNode, useContext } from "react"
import { useLocation } from "react-router-dom"
import AuthContext from "../context/AuthContext"
import { loginSignUpObject } from "../interfaces"

const SignUpLogin = () => {
    const context = useContext(AuthContext) 
    console.log(context, "HELLO")
    const {pathname} = useLocation()

    const formfields: string[] = [ "Username:", "Email:", "Password:", "Re-enter your password:"]

    const mapFormFields = (formFields: string[]): ReactNode =>{
        return formFields.map((formField: string, index: number) : ReactNode => {
            if(formField === "Email:" || formField === "Re-enter your password:"){
                if(pathname.includes("login")){
                    return;
                }
            } 

            let id = ""

            if(formField === "Re-enter your password:"){
                id = "password2"
            } else {
                id = formField[0].toLowerCase() + formField.slice(1, formField.length - 1)
            }

            return (
                <div className="flex flex-col" key={index}>
                    <label>{formField}</label>
                    <input id={id} className="border-2"></input>
                </div>
            )
        }
        )
    }

    const handleLoginSignUp = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const target = event.currentTarget

        if(pathname.includes("signup")){
            if(target.password.value !== target.password2.value){
                throw new Error("Your passwords did not match!")
            }
        }
        
        const accountObj: loginSignUpObject = {
            username: target.username.value,
            password: target.password.value,
        }

        if(pathname.includes("signup")){
            accountObj.email = target.email.value
            accountObj.method = "signup"
            context!.loginSignUp(accountObj)
        } else {
            accountObj.method = "login"
            context!.loginSignUp(accountObj)
        }
    }
        
    return (
        <div className="flex flex-col w-full h-full justify-center items-center">
            <form onSubmit={handleLoginSignUp} className={`flex flex-col justify-evenly border-2 w-[350px] h-[450px] ${!pathname.includes("login") ? "mb-[125px]" : "" }`}>
                <div> {pathname.includes("login") ? "Login" : "Create Account" } </div>
                {
                    mapFormFields(formfields)
                }
            <button className="border-2">Submit</button>
            <div>
                {pathname.includes("login") ? "" :
                    <div className="flex justify-center">
                        <label className="mr-1">Have an account already?</label>
                        <a href="/login"> Log in here! </a>
                    </div>
                }
            </div>
            </form>
            {pathname.includes("login") ? 
                <div className="flex flex-col justify-center items-center mb-[77px]">
                    <label>Don't have an account?</label>
                    <a href="/signup">
                        Make a new one here!
                    </a>
                </div>  :
                ""
            }
        </div>
    )
}

export default SignUpLogin