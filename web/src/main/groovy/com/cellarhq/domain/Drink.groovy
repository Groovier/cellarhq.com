package com.cellarhq.domain

import com.github.slugify.Slugify
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors

@CompileStatic
@InheritConstructors
class Drink extends com.cellarhq.generated.tables.pojos.Drink {

    Drink() {
        searchable = true
        locked = false
        needsModeration = true
    }

    @Override
    void setSlug(String slug) {
        super.slug = new Slugify().slugify(slug)
    }
}