package com.faustogomez.cookit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_recipe.view.*

@Parcelize
data class Recipe(val name: String, val imageURL:String, val steps: List<String>): Parcelable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recipe_list.layoutManager = LinearLayoutManager(this)

        val recipes = listOf(
            Recipe("Chocolate Chip Cookies", "https://images.pexels.com/photos/230325/pexels-photo-230325.jpeg", listOf("Step 1", "Step 2")),
            Recipe("Best Brownies", "https://images.pexels.com/photos/45202/brownie-dessert-cake-sweet-45202.jpeg", listOf("Step 1", "Step 2", "Step 3")),
            Recipe("Banana Bread", "https://images.pexels.com/photos/830894/pexels-photo-830894.jpeg", listOf("Step 1", "Step 2"))
        )

        recipe_list.adapter = RecipeAdapter(recipes, this, recipeSelected = {
            val intent = RecipeDetailActivity.newIntent(it, this)
            startActivity(intent)
        })

    }
}

    private class RecipeAdapter(val recipes: List<Recipe>, val context: Context, val recipeSelected: (Recipe) -> Unit): RecyclerView.Adapter<RecipeViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
            return RecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false))
        }

        override fun getItemCount(): Int {
            return recipes.count()
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            val recipe = recipes[position]

            holder.itemView.setOnClickListener {
                recipeSelected(recipe)
            }

            holder.itemView.item_recipe_name.text = recipe.name
            Glide.with(context).load(recipe.imageURL).into(holder.itemView.item_recipe_image)
        }
    }

private class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view)
