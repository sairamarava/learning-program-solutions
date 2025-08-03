import './Stylesheets/mystyle.css'

export const CalculateScore=({Name,school,total,goal})=>{
    const percentage=(total/goal)*100;
    const grade=percentage>=90?'A':percentage>=80?'B':percentage>=70?'C':percentage>=60?'D':'F';
    const result=percentage>=60?'Pass':'Fail';
    return(
        <div className="formatstyle">
            <h1>Score Calculator</h1>
            <h2 className="Name">Name: {Name}</h2>
            <h2 className="School">School: {school}</h2>
            <h2 className="Total">  Total Marks: {total}</h2>
            <h2 className="Score">Goal Marks: {goal}</h2>
            <h2>Percentage: {percentage}%</h2>
            <h2>Grade: {grade}</h2>
            <h2>Result: {result}</h2>
            <h2>{result === 'Pass' ? 'Congratulations!' : 'Better luck next time!'}</h2>
        </div>
    )
}