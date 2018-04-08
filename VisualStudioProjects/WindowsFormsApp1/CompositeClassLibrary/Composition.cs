using CompositeClassLibrary.CompositePattern;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CompositeClassLibrary
{
    public class Composition : Component
    {
        //private List<Component> components;
        private Composite _composite;

        public Composition(string name, Compositor compositor) 
            : base(name)
        {
            //components = new List<Component>();
        }

        public override void Add(Component component)
        {
            //components.Add(component);
            _composite = (Composite)component;
        }

        public override void Draw(Graphics graphics)
        {
            //components.ForEach(a => a.Draw(graphics));
            _composite.Draw(graphics);
        }

        public override void Remove(Component component)
        {
            throw new NotImplementedException();
        }

        private void Compose() // TODO: udskift med compositor
        {

            

        }
    }
}
