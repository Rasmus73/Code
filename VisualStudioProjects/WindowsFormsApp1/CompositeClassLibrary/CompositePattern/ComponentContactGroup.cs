using Model.ValueTypes;
using System;

namespace CompositeClassLibrary.CompositePattern
{
    public class ComponentContactGroup : IntervalComponent, IComponent
    {
        public string Name { get; set; }

        public ComponentContactGroup(string name, IntervalType interval)
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