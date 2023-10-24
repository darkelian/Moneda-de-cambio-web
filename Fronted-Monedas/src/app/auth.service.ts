import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'URL_DE_TU_BACKEND'; // Cambia esto por la URL de tu backend

  constructor(private http: HttpClient) { }

  login(usuario: string, contrasena: string) {
    const body = { usuario, contrasena };
    return this.http.post(`${this.apiUrl}/ruta_del_login`, body); // Ajusta la ruta según tu backend
  }
}
