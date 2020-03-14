const express=require("express")
const app=express();

const mongoClient=require('mongodb').MongoClient;

const url="mongodb://localhost:27017"

app.use(express.json())

mongoClient.connect(url,(err,db)=>{
    if(err) console.log("error");
    const myDB=db.db('mydb');
    const collection=myDB.collection('myTable');


app.post('\login',(request,response)=>{
    const newUser={
        email:request.body.email,
        password:request.body.password
    }


    collection.findOne(newUser,(err,result)=>{

        if(result!=null){

        const objtosend={
            name:result.name,
            email:result.email
        }
        response.status(200).send(JSON.stringify(objtosend))
    }
    else{
        response.status(404).send()
    }
    })











  

    // collection.findOne(newUser,(err,result)=>{
    //     if(result==null){
    //         collection.insertOne(newUser,(err,result)=>{
    //             response.status(200).send()
    //         })
    //     }
    //     else{
    //         response.status(400).send();
    //     }
    // })

})


})



app.listen(6000,()=>{
    console.log("app listening on the port 6000");
});