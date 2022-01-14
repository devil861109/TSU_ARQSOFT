import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'
import TaskService from '../services/TaskService'

const ListTaskComponent = () => {

    const [tasks, setTasks] = useState([])

    useEffect(() => {
        getAllTasksFromDB();
    }, [])

    const getAllTasksFromDB = () => {
        TaskService.getAllTasks().then(response => {
            setTasks(response.data)
            console.log(response.data)
        }).catch(error => {
            console.log(error)
        })
    }

    const deleteTask = (taskId) => {
        TaskService.deleteTaskById(taskId).then((response) => {
            getAllTasksFromDB();
        }).catch(error => {
            console.log(error)
        })
    }

    return (
        <div className='container'>
            <h2 className='text-center'>Tasks List</h2>
            <Link to='/add-task' className='btn btn-primary mb-2'>Add Task</Link>
            <table className='table table-striped table-bordered'>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Description</th>
                      <th>Status</th>
                      <th>Creation Date</th>
                      <th>Actions</th>
                    </tr>
                  </thead>
                  <tbody>
                      {
                          tasks.map(
                              task =>
                              <tr key = {task.id}>
                                <td>{task.id}</td>
                                <td>{task.description}</td>
                                <td>{task.status}</td>
                                <td>{task.creationDate}</td>
                                <td>
                                    <Link className='btn btn-info' to={`/edit-task/${task.id}`}>Update</Link>
                                    <button className='btn btn-danger' onClick={() => deleteTask(task.id)} 
                                    style={{marginLeft:"10px"}}>Delete</button>
                                </td>
                              </tr>
                          )
                      }
                  </tbody>
                </table>
        </div>
    )
}

export default ListTaskComponent