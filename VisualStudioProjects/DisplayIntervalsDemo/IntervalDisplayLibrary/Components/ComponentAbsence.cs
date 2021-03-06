﻿using IntervalDisplayLibrary.Components.Interface;
using Model.ValueTypes;
using System;

namespace IntervalDisplayLibrary.Components
{
    public class ComponentAbsence : IntervalComponent, IComponent
    {
        public string Name { get; set; }

        public ComponentAbsence(string name, IntervalType interval)            
            : base(name, interval)
        {
            Name = name;
        }

        public void Add(IComponent component)
        {
            throw new NotImplementedException();
        }
    }
}