package com.faustogomez.cookit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recipe_detail.*
import kotlinx.android.synthetic.main.step_item.view.*

class RecipeDetailActivity: AppCompatActivity(){
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        steps_list.layoutManager = LinearLayoutManager(this)

        val recipe: Recipe = intent?.extras?.getParcelable(recipeExtra)!!

        recipe_name.text = recipe.name
        Glide.with(this).load(recipe.imageURL).into(recipe_img)


        steps_list.adapter = RecipeStepAdapter(recipe.steps, this)
    }

    companion object{
        const val recipeExtra = "RECIPE"

        fun newIntent(recipe: Recipe, context: Context): Intent{
            val intent = Intent(context, RecipeDetailActivity::class.java)
            intent.putExtra(recipeExtra, recipe)
            return intent
        }
    }
}

private class RecipeStepAdapter(val recipeSteps: List<String>, val context: Context): RecyclerView.Adapter<RecipeStepViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepViewHolder {
        return RecipeStepViewHolder(LayoutInflater.from(context).inflate(R.layout.step_item, parent, false))
    }

    override fun getItemCount(): Int {
        return recipeSteps.count()
    }

    override fun onBindViewHolder(holder: RecipeStepViewHolder, position: Int) {
        val step = recipeSteps[position]

        holder.itemView.step_recipe.text = step
    }
}

private class RecipeStepViewHolder(view: View): RecyclerView.ViewHolder(view)