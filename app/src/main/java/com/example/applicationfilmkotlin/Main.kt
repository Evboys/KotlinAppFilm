package com.example.applicationfilmkotlin



abstract class Piece(){
    abstract val largeur: Float
    abstract val longueur: Float
    abstract val nom:String
    fun surface():Float{
        return longueur*largeur
    }

}
class Cuisine():Piece() {
    override val largeur = 2.0F
    override val longueur = 3.0F
    override val nom = "cuisine"

}
class Salon():Piece(){
    override val largeur = 4.0F
    override val longueur = 1.0F
    override val nom = "salon"
}

class Etudiant(val name:String,val promo: String,val matieres: List<String>){

}

fun main(){
    println("Coucou")
    val test=listOf(Cuisine(),Salon())

    for(piece in test){
        println(piece.surface())
        println(piece.nom)
    }
    val etudiants = listOf(
        Etudiant("Paul","2025", listOf("mobile","Web","BDD")),
        Etudiant("Yazid","2024", listOf("mobile","Android","Réseau")),
        Etudiant("Caroline","2025", listOf("SE","Anglais")),
    )
    etudiants.forEach{ println(it.name) }
    etudiants.filter{it.matieres.last() !== it.matieres[1]}.map {it.name }.forEach{println(it)}




}
