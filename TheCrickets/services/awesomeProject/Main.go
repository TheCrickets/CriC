package main

import (
	"net/http"
)

func saveHandler(w http.ResponseWriter, r *http.Request) {
	// allow cross domain AJAX requests
	w.Header().Set("Access-Control-Allow-Origin", "*")
}

func main() {
	fs := http.FileServer(http.Dir("D:\\CriC\\CriC\\TheCrickets\\viewsBackup\\"))
	http.Header.Add(http.Header{}, "Access-Control-Allow-Origin", "*")
	http.Handle("/static/", http.StripPrefix("/static/", fs))
	http.ListenAndServe(":3000", nil)
}