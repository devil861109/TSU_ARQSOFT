import React, { useState, useEffect } from 'react'
import {useNavigate, useParams } from 'react-router-dom'
import TaskService from '../services/TaskService'

const AddTaskComponent = () => {
    
    const [description, setDescription] = useState('')
    const [status, setStatus] = useState('')
    const [creationDate, setCreationDate] = useState('')
    const navigate = useNavigate();
    const {id} = useParams();

    const saveOrUpdateTask = (e) => {
        e.preventDefault();
        const task = {description, status, creationDate}
        if (id) {
            TaskService.updateTask(id, task).then((response) => {
                navigate('/tasks', { replace: true })
            }).catch(error => {
                console.log(error)
            })
        } else {
            TaskService.createTask(task).then((response) => {
                console.log(response.data)
                navigate('/tasks', { replace: true })
            }).catch(error => {
                console.log(error)
            })
        }
    }

    useEffect(() => {
        TaskService.getTaskById(id).then((response) => {
            setDescription(response.data.description)
            setStatus(response.data.status)
            setCreationDate(response.data.creationDate)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {
        if (id) {
            return <h2 className='text-center'>Update Task</h2>
        } else {
            return <h2 className='text-center'>Add Task</h2>
        }
    }

    return (
        <div>
            <br/><br/>
            <div className='container'>
                <div className='row'>
                    <div className='card col-md-6 offset-md-3 offset-md-3'>
                        {
                            title
                        }
                        <div className='card-body'>
                            <form>
                                <div className='form-group mb-2'>
                                    <label className='form-label'>Description</label>
                                    <input className='form-control' type='text' placeholder='Enter Description' name='description'
                                    value={description} onChange={(e) => setDescription(e.target.value)}></input>
                                    <label className='form-label'>Status</label>
                                    <input className='form-control' type='text' placeholder='Enter Status' name='status'
                                    value={status} onChange={(e) => setStatus(e.target.value)}></input>
                                    <label className='form-label'>Creation Date</label>
                                    <input className='form-control' type='text' placeholder='Enter Creation Date' name='creationDate'
                                    value={creationDate} onChange={(e) => setCreationDate(e.target.value)}></input>
                                </div>
                                <button className='btn btn-success' onClick={(e) => saveOrUpdateTask(e)}>Submit</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddTaskComponent