import axios from "axios";
import {useState, useEffect} from "react"
import { productObject } from "../interfaces";
import { useParams } from "react-router-dom";

const SingleProduct = () => {
    const [product, setProduct]  = useState<productObject|null>(null);

    const {productId} = useParams()

    useEffect(
        () => {
            const getSingleProduct = async () => {
              const { data }  =  await axios.get(`http://localhost:8080/api/product/${productId}`);
              console.log(data);
              setProduct(data);
            } 
            getSingleProduct()
        }, []
    )

    const concateSecondaryInfo = (secondaryInfo : string[]) : JSX.Element => {
        let combinedString = ""

        for(let i = 0; i < secondaryInfo.length; i++){
            if(i !== secondaryInfo.length - 1){
                combinedString += `${secondaryInfo[i]}, `
            } else {
                combinedString += secondaryInfo[i]
            } 
        }

        return <div>{combinedString}</div>
    }
    
    console.log(product)
    return (
        <>
            <div className="w-screen h-screen flex flex-col justify-center items-center">
                {!product?
                    "Sorry, this monster seems to be missing!": 
                    <>
                        <div className="flex border-2 p-2">
                            <img src={product.image} className="mr-5"/>
                            <div className="flex flex-col">
                                <div>{product.name}</div>
                                <div className="w-72 my-2">{product.description}</div>
                                <div className="mb-2">{product.price}</div>
                                <div className="flex">
                                    <label className="mr-1">
                                        Common Drops: 
                                    </label>
                                    {concateSecondaryInfo(product.drops)}
                                </div>
                                <div className="flex">
                                    <label className="mr-1">
                                        Common Locations: 
                                    </label>
                                    {concateSecondaryInfo(product.common_locations)}
                                </div>
                            </div>
                        </div>
                        <div className="flex flex-col justify-center items-center">
                            <div className="border-2 flex justify-center">
                                <label className="mx-2">Quantity:</label>
                                <input type="number" name="itemQuantity" defaultValue="1" className="w-8"/>
                            </div>
                            <button className="border-2 text-center">Add to cart</button>
                        </div>
                    </>
                }
            </div>
        </>
    )

}

export default SingleProduct