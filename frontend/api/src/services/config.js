import axios from 'axios'
axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';

export const http = axios.create({
    baseURL: 'http://localhost:3333/api/',
})