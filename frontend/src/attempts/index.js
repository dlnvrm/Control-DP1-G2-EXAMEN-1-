import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";
import tokenService from "../services/token.service";

export default function AttemptsListing() {
    const [attempts, setAttempts] = useState([]);
    const jwt = tokenService.getLocalAccessToken();

    useEffect(() => {
        fetch("/api/v1/attempts", {
            headers: { Authorization: `Bearer ${jwt}` },
        })
            .then((response) => response.json())
            .then((data) => setAttempts(data))
            .catch((error) => console.log(error));
    }, [jwt]);

    if (attempts.length === 0) {
        return <h1>NO ATTEMPTS</h1>;
    }

    return (
        <Table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Start</th>
                    <th>Finish</th>
                    <th>Player</th>
                    <th>Problem</th>
                    <th>Difficulty</th>
                </tr>
            </thead>
            <tbody>
                {attempts.map((a) => (
                    <tr key={a.id}>
                        <td>{a.name}</td>
                        <td>{a.start}</td>
                        <td>{a.finish}</td>
                        <td>{a.player?.username}</td>
                        <td>{a.problem?.name}</td>
                        <td>{a.problem?.difficultyLevel}</td>
                    </tr>
                ))}
            </tbody>
        </Table>
    );
}